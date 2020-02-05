package com.mangotele.generate;

import com.google.gson.Gson;
import com.mangotele.generate.types.Command;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author kyyakime
 */
public class CommandMessageConverter implements MessageConverter {
    private final Gson gson = new Gson();

    public CommandMessageConverter() {
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        Command msg = (Command) object;
        byte[] body;
        if (msg != null) {
            body = gson.toJson(msg).getBytes();
        } else {
            return null;
        }
        return MessageBuilder.withBody(body).andProperties(messageProperties).build();
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        byte[] body = message.getBody();
        if (body != null && body.length > 0) {
            String command = new String(body);
            return gson.fromJson(command, Command.class);
        }
        return null;
    }
}