package org.cleaning.crm.worker.mapper;

import org.cleaning.crm.worker.model.WorkerMainInfoResponse;

import java.util.List;

public interface TupleModelMapper {

	List<WorkerMainInfoResponse> toMainInfoResponse();
}
