package com.infnet.rental.messaging;

import com.infnet.rental.messaging.commands.ApproveRentalCommand;
import com.infnet.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class Consumers {
    private final RentalService service;

    @Bean
    public Consumer<ApproveRentalCommand> approveRentalCommand() {
        return cmd -> service.approve(cmd.getRentalId());
    }
}