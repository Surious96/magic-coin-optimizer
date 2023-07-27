package com.dataart.task.magic.coin.optimizer.bank;

import com.dataart.task.magic.coin.optimizer.domain.Cash;
import com.dataart.task.magic.coin.optimizer.service.CashOptimizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankService {

    final CashOptimizer cashOptimizer;

    /**
     * Receives the cash amount based on the provided arguments.
     *
     * @param args an array of three numeric arguments representing the amount of moonstones, shards, and florins
     * @return a Cash object representing the received cash amount
     * @throws IllegalArgumentException if the number of arguments is not exactly three or if the arguments are not valid numbers
     */
    public Cash receiveCash(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Please provide exactly three numeric arguments.");
        }

        try {
            int moonstones = Integer.parseInt(args[0]);
            int shards = Integer.parseInt(args[1]);
            int florins = Integer.parseInt(args[2]);
            return Cash.of(moonstones, shards, florins);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Invalid numeric argument(s). Please provide valid numbers.");
        }
    }

    public Cash optimize(Cash input) {
        return cashOptimizer.optimize(input);
    }

}
