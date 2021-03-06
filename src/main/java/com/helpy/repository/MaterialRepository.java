package com.helpy.repository;
import com.helpy.dto.MaterialesResumenDTO;
import com.helpy.model.Material;
import com.helpy.model.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


import java.util.List;

@Repository
public interface MaterialRepository extends GenericRepository<Material, Long> {
    List<Material> findMaterialsByExpert_Id(Long id);
}
