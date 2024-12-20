package com.hepi.music_api.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Acknowledgement {
    private String transactionId;
    private int responseCode;
    private String responseMessage;
    private String customizedMessage;
    private String timestamp;
}
