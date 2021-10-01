package com.helpy.service;

import com.helpy.dto.ExpertResponse;
import com.helpy.model.Expert;

import java.util.List;

public interface ExpertService extends CrudService<Expert, Long> {
    List<Expert> getAllByGameId(Long gameId);
}
