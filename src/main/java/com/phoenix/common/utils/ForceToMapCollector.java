package com.phoenix.common.utils;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ForceToMapCollector<T, K, V> implements Collector<T, Map<K, V>, Map<K, V>> {

	private Function<? super T, ? extends K> keyMapper;

	private Function<? super T, ? extends V> valueMapper;

	public ForceToMapCollector(Function<? super T, ? extends K> keyMapper,
			Function<? super T, ? extends V> valueMapper) {
		super();
		this.keyMapper = keyMapper;
		this.valueMapper = valueMapper;
	}

	@Override
	public BiConsumer<Map<K, V>, T> accumulator() {
		return (map, element) -> map.put(keyMapper.apply(element), valueMapper.apply(element));
	}

	@Override
	public Supplier<Map<K, V>> supplier() {
		return HashMap::new;
	}

	@Override
	public BinaryOperator<Map<K, V>> combiner() {
		return null;
	}

	@Override
	public Function<Map<K, V>, Map<K, V>> finisher() {
		return null;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
	}

}
