package com.sbm.module.onlineleasing.interactive.website.modality.domain;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.onlineleasing.base.modality.domain.TOLModality;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ModalityQuery {

	public Specification<TOLModality> findAll() {
		return new SpecificationBuilder<TOLModality>()
				.build();
	}

}
