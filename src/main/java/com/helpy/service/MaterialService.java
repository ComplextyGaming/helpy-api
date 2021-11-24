package com.helpy.service;

import com.helpy.dto.MaterialesResumenDTO;
import com.helpy.model.Material;

import java.util.List;

public interface MaterialService extends CrudService<Material, Long> {

    List<Material> getByExpertId(Long id) throws Exception;

    byte[] generarReporte();
    List<MaterialesResumenDTO> listarResumen();

    List<Material> getByExpertIdAndTagIg(Long expertId, Long tagId);
}
