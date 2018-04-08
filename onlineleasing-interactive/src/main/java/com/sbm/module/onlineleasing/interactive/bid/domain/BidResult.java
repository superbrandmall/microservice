package com.sbm.module.onlineleasing.interactive.bid.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

@Data
public class BidResult<T> {

	@Valid
	@NotEmpty
	private List<T> bidResultReceives;

}
