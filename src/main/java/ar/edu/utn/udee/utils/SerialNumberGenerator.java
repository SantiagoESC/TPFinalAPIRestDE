package ar.edu.utn.udee.utils;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Random;

@NoArgsConstructor
public class SerialNumberGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(SerialNumberGenerator.class);

    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private int splitInto = 1;
    private char splitter = '-';
    private boolean isToSplit = false;
    private int length = 4;
    private final Random random = new SecureRandom();

    /**
     * Generates random serial number on the fry
     * @param length Defines how long your serial number can be
     */
    public SerialNumberGenerator(int length) {
        this.length = length;
    }

    public SerialNumberGenerator split(int splitInto, char splitter) {
        this.splitInto = splitInto;
        this.splitter = splitter;
        isToSplit = true;
        return this;
    }

    private String split() throws InvalidSerialNumberLengthException {
        if (length % splitInto != 0) {
            throw new InvalidSerialNumberLengthException();
        }

        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        int tempLength = length;

        while (tempLength > 0) {
            if (spacer == (length / splitInto)) {
                sb.append(splitter);
                spacer = 0;
            }
            tempLength--;
            spacer++;
            sb.append(pickRandomCharacter());
        }

        return sb.toString();
    }

    private char pickRandomCharacter() {
        return ALPHABETS.charAt(random.nextInt(length));
    }

    /**
     * Generates random char string
     */
    public String generate() {

        StringBuilder serial = new StringBuilder();

        // check if need splitting serial number
        if (isToSplit) {
            try {
                return split();
            } catch (InvalidSerialNumberLengthException e) {
                LOG.error(MessageFormat.format("Error: {0}", e.getMessage()));
            }
        }
        // generate whole serial number (no split)
        for (int i = 0; i < length; i++) serial.append(pickRandomCharacter());
        return serial.toString();
    }


    @Override
    public String toString() {
        return "Generates your serial numbers on the fry";
    }

    private static class InvalidSerialNumberLengthException extends Exception {
        InvalidSerialNumberLengthException() {
            super("Serial number length must be divisible by the number of parts to perform split action.");
        }
    }
}
