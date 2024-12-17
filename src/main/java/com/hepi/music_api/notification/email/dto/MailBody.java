package com.hepi.music_api.notification.email.dto;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
