package com.dataart.task.magic.coin.optimizer;

import com.dataart.task.magic.coin.optimizer.domain.Cash;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MagicCoinOptimizerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(MagicCoinOptimizerApplication.class, args);
        log.info("APPLICATION FINISHED");
    }

    public static Cash receiveInput(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Please provide exactly three numeric arguments.");
        }

        try {
            int moonstones = parseInt(args[0]);
            int shards = parseInt(args[1]);
            int florins = parseInt(args[2]);
            return Cash.of(florins, shards, moonstones);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Invalid numeric argument(s). Please provide valid numbers.");
        }
    }

    private static int parseInt(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }

    @Override
    public void run(String... args) {
        Cash customersInput = receiveInput(args);
        System.out.print("Input: " + customersInput);
        System.out.printf(" => weight is %d dragonscales%n", customersInput.getWeightInDragonScales());
    }

}
