package com.mangotele.generate;

import com.mangotele.generate.types.Command;
import com.mangotele.generate.types.CommandType;
import com.mangotele.generate.types.FileMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class GenerateApplication {

	private static final Logger logger = LoggerFactory.getLogger(GenerateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GenerateApplication.class, args);
	}

	private final RabbitTemplate template;
	private final DirectExchange direct;
	private final String urlMaster;
	private final String routingKey;
	private final String file;

	@Autowired
	public GenerateApplication(
			@Value("${urlmaster}") String urlMaster,
			@Value("${routingkey}") String routingKey,
			RabbitTemplate template,
			DirectExchange direct,
			@Value("${filepath}") String file) {
		this.urlMaster = urlMaster;
		this.routingKey = routingKey;
		this.template = template;
		this.direct = direct;
		this.file = file;
	}

	private static final AtomicLong requestId = new AtomicLong(0);

	@Bean
	public CommandLineRunner generate() {
		return args -> {
			logger.info("Start application");
			Path path = Paths.get(file);
			try {
				Files.lines(path).forEach(line -> {
					try {
						Long filePathId = Long.valueOf(line);
						logger.info("filePathId = {}", filePathId);
						Command command = createCommand(filePathId, urlMaster);
						template.convertAndSend(direct.getName(), routingKey, command);
					} catch (Exception e) {
						logger.error("Error: ", e);
					}
				});
			} catch (IOException ex) {
				logger.error("Error: ", ex);
			} finally {
				System.exit(0);
			}
		};
	}

	public static Command createCommand(Long filePathId, String urlMaster) {
		Command cmd = new Command();
		cmd.setCommandType(CommandType.ADD_FILE);
		cmd.setUrlMaster(urlMaster);
		cmd.setFileServerDir("");
		cmd.setRequestId(String.valueOf(requestId.incrementAndGet()));

		FileMessage fm = new FileMessage();
		fm.setFileId(0L);
		fm.setFileName("");
		fm.setFileDesc("");
		fm.setFilePathId(filePathId);
		fm.setLogBuilder(new StringBuilder(""));
		fm.setSavedFileSize(0);
		fm.setAppIpAddr("");
		fm.setSystemRecordId(0L);
		fm.setSystemTableId(0L);

		cmd.setFileMessage(fm);
		return cmd;
	}
}
