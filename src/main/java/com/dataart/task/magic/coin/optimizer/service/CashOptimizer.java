package com.dataart.task.magic.coin.optimizer.service;

import com.dataart.task.magic.coin.optimizer.domain.Cash;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.dataart.task.magic.coin.optimizer.domain.Coin.MOONSTONE;
import static com.dataart.task.magic.coin.optimizer.domain.Coin.SHARD;

@Component
public class CashOptimizer {

    public Cash optimize(Cash customerCash) {
        double cashInFlorins = customerCash.exchangeToFlorins();
        List<Cash> exchangedCashes = new ArrayList<>();
        // checking with only florins weight
        Cash onlyFlorinsCash = Cash.of(0, 0, (int) cashInFlorins);
        exchangedCashes.add(onlyFlorinsCash);

        // checking with florins and shards
        int shardsMaxCount = (int) (cashInFlorins / SHARD.exchangeToFlorins(1));
        int florins = (int) (cashInFlorins - SHARD.exchangeToFlorins(shardsMaxCount));

        Cash florinsAndShardsCash = Cash.of(0, shardsMaxCount, florins);
        exchangedCashes.add(florinsAndShardsCash);

        // checking with florins and moonstones
        int moonstonesMaxCount = (int) (cashInFlorins / MOONSTONE.exchangeToFlorins(1));
        florins = (int) (cashInFlorins - MOONSTONE.exchangeToFlorins(moonstonesMaxCount));

        Cash florinsAndMoonstonesCash = Cash.of(moonstonesMaxCount, 0, florins);
        exchangedCashes.add(florinsAndMoonstonesCash);

        // checking with florins, shards, and moonstones
        moonstonesMaxCount = (int) (cashInFlorins / MOONSTONE.exchangeToFlorins(1));
        cashInFlorins -= MOONSTONE.exchangeToFlorins(moonstonesMaxCount);

        shardsMaxCount = (int) (cashInFlorins / SHARD.exchangeToFlorins(1));
        cashInFlorins -= SHARD.exchangeToFlorins(shardsMaxCount);

        Cash cashInAllCoins = Cash.of(moonstonesMaxCount, shardsMaxCount, (int) cashInFlorins);
        exchangedCashes.add(cashInAllCoins);

        // determining the cash with minimal weight
        return exchangedCashes.stream()
                .min(Comparator.comparingInt(Cash::getWeightInDragonScales)).orElse(null);
    }
}
