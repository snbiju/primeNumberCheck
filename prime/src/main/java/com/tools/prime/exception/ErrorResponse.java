package com.tools.prime.exception;


import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

        private LocalDateTime timestamp;
        private String message;
        private List<String> details;




        public ErrorResponse(LocalDateTime timestamp, String message, List<String> details) {
            super();
            this.timestamp = timestamp;
            this.message = message;
            this.details = details;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getDetails() {
            return details;
        }

}
