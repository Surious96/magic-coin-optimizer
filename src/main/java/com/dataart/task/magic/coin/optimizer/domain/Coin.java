package com.dataart.task.magic.coin.optimizer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Coin {
    FLORIN(3),
    SHARD(7),
    MOONSTONE(24),
    ;

    public static final double DRAGON_SCALE_IN_GRAM = 1.345;

    private final int weightInDragonScales;

    public double exchangeToFlorins(double coinNumber) {
        return switch (this) {
            case FLORIN -> coinNumber;
            case SHARD -> coinNumber * 8 / 3;
            case MOONSTONE -> (coinNumber * 8 / 3) * 13 / 5;
        };
    }

}
