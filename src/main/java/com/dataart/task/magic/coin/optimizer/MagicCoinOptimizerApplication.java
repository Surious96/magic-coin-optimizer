package com.dataart.task.magic.coin.optimizer;

import com.dataart.task.magic.coin.optimizer.bank.BankService;
import com.dataart.task.magic.coin.optimizer.domain.Cash;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class MagicCoinOptimizerApplication implements CommandLineRunner {

    final BankService bankService;

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(MagicCoinOptimizerApplication.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        Cash customerInput = bankService.receiveCash(args);

        Cash optimizedCash = bankService.optimize(customerInput);
        System.out.print("Input: " + customerInput);
        System.out.printf(" => weight is %d dragonscales%n", customerInput.getWeightInDragonScales());
        System.out.print("Output: " + optimizedCash);
        System.out.printf(" => weight is %d dragonscales%n", optimizedCash.getWeightInDragonScales());
    }

}
