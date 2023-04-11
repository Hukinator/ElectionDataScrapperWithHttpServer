package com.hukinator.ElectionDataScraper;
public class tweetParsingException extends RuntimeException {
        public tweetParsingException() {
        }

        public tweetParsingException(String message) {
            super(message);
        }

        public tweetParsingException(String message, Throwable cause) {
            super(message, cause);
        }

        public tweetParsingException(Throwable cause) {
            super(cause);
        }
    }
