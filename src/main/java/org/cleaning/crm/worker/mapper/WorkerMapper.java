package org.cleaning.crm.worker.mapper;

import jakarta.persistence.Tuple;
import org.cleaning.crm.worker.model.WorkerMainInfoResponse;
import org.cleaning.crm.worker.model.WorkerResponse;

import java.util.List;

public class WorkerMapper implements TupleModelMapper {

	private final List<Tuple> tuples;

	public WorkerMapper(List<Tuple> tuples) {
		this.tuples = tuples;
	}

	@Override
	public List<WorkerMainInfoResponse> toMainInfoResponse() {
		return tuples.stream().map(tuple -> WorkerMainInfoResponse.builder()
					.workerId(tuple.get(0,Long.class))
					.firstName(tuple.get(1, String.class))
					.lastName(tuple.get(2, String.class))
					.hourRate(tuple.get(3, Integer.class))
				.build()).toList();
	}


}
