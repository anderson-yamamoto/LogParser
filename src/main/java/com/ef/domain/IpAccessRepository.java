package com.ef.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface IpAccessRepository {

	void insert(IpAccess ipAccess);

	void batchInsert(List<IpAccess> list);

	List<String> findIpsWithThresholdBetweenDates(int threshold, LocalDateTime start, LocalDateTime end);

}