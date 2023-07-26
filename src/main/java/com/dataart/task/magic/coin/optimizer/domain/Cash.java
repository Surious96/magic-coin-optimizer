package com.dataart.task.magic.coin.optimizer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static com.dataart.task.magic.coin.optimizer.domain.Coin.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cash {
    private int moonstones;
    private int shards;
    private int florins;

    public static Cash of(int moonstones, int shards, int florins) {
        if (isValidNumberOfCoins(florins) && isValidNumberOfCoins(shards) && isValidNumberOfCoins(moonstones)) {
            return new Cash(moonstones, shards, florins);
        }
        throw new IllegalArgumentException("Invalid number of coins provided");
    }

    public int getWeightInDragonScales() {
        return moonstones * MOONSTONE.getWeightInDragonScales() + shards * SHARD.getWeightInDragonScales() +
                florins * FLORIN.getWeightInDragonScales();
    }


    public double getWeightInGrams() {
        return getWeightInDragonScales() * DRAGON_SCALE_IN_GRAM;
    }

    private static boolean isValidNumberOfCoins(int numberOfCoins) {
        return numberOfCoins >= 0;
    }

    @Override
    public String toString() {
        StringBuilder cashString = new StringBuilder();
        if (moonstones > 0) {
            cashString.append(moonstones)
                    .append(" moonstones,");
        }
        if (shards > 0) {
            cashString.append(shards)
                    .append(" shards,");
        }
        if (florins > 0) {
            cashString.append(florins)
                    .append(" florins");
        }
        return cashString.toString();
    }

}
