package com.hukinator.ElectionDataScraper;

public class PartyListException extends RuntimeException {
        public PartyListException() {
        }

        public PartyListException(String message) {
            super(message);
        }

        public PartyListException(String message, Throwable cause) {
            super(message, cause);
        }

        public PartyListException(Throwable cause) {
            super(cause);
        }
}
