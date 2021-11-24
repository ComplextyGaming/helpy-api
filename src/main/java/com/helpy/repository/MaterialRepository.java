package com.helpy.repository;

import com.helpy.dto.MaterialesResumenDTO;
import com.helpy.model.Material;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialRepository extends GenericRepository<Material, Long> {
}
