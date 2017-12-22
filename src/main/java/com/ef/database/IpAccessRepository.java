package com.ef.database;

import java.time.LocalDateTime;
import java.util.List;

import com.ef.domain.IpAccess;

public interface IpAccessRepository {

	void insert(IpAccess ipAccess);

	void batchInsert(List<IpAccess> list);

	List<String> findIpsWithThresholdBetweenDates(int threshold, LocalDateTime start, LocalDateTime end);

}