package com.ajavaguy.kafka.message;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreated {
    Integer messageId;
    String content;
}
