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
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.stream.Collectors.toList;

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
    private final String folder;

    @Autowired
    public GenerateApplication(
            @Value("${urlmaster}") String urlMaster,
            @Value("${routingkey}") String routingKey,
            RabbitTemplate template,
            DirectExchange direct,
            @Value("${folder}") String folder) {
        this.urlMaster = urlMaster;
        this.routingKey = routingKey;
        this.template = template;
        this.direct = direct;
        this.folder = folder;
    }

    private static final AtomicLong requestId = new AtomicLong(0);

    @Bean
    public CommandLineRunner generate() {
        return args -> {
            logger.info("Start application");
            try {
                List<Path> files = Files.walk(Paths.get(folder))
                        .filter(Files::isRegularFile)
                        .collect(toList());
                for (Path file : files) {
                    logger.info("Load file {}", file);
                    Files.lines(file).forEach(s -> {
                        try {
                            Long filePathId = Long.valueOf(s);
                            logger.info("filePathId = {}", filePathId);
                            Command command = createCommand(filePathId, urlMaster);
                            template.convertAndSend(direct.getName(), routingKey, command);
                        } catch (Exception e) {
                            logger.error("Error: ", e);
                        }
                    });
                }
                ;
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
