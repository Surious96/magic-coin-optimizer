package com.dataart.task.magic.coin.optimizer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static com.dataart.task.magic.coin.optimizer.domain.Coin.*;

/**
 * The Cash class represents a collection of coins, including moonstones, shards, and florins.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cash {
    private int moonstoneAmount;
    private int shardAmount;
    private int florinAmount;

    public static Cash of(int moonstones, int shards, int florins) {
        if (isValidNumberOfCoins(florins) && isValidNumberOfCoins(shards) && isValidNumberOfCoins(moonstones)) {
            return new Cash(moonstones, shards, florins);
        }
        throw new IllegalArgumentException("Invalid number of coins provided");
    }

    public int getWeightInDragonScales() {
        return moonstoneAmount * MOONSTONE.getWeightInDragonScales() + shardAmount * SHARD.getWeightInDragonScales() +
                florinAmount * FLORIN.getWeightInDragonScales();
    }

    public double exchangeToFlorins() {
        return MOONSTONE.exchangeToFlorins(moonstoneAmount) + SHARD.exchangeToFlorins(shardAmount) + florinAmount;
    }

    private static boolean isValidNumberOfCoins(int numberOfCoins) {
        return numberOfCoins >= 0;
    }

    @Override
    public String toString() {
        StringBuilder cashString = new StringBuilder();
        if (moonstoneAmount > 0) {
            cashString.append(moonstoneAmount)
                    .append(" moonstones,");
        }
        if (shardAmount > 0) {
            cashString.append(shardAmount)
                    .append(" shards,");
        }
        if (florinAmount > 0) {
            cashString.append(florinAmount)
                    .append(" florins");
        }
        return cashString.toString();
    }

}
