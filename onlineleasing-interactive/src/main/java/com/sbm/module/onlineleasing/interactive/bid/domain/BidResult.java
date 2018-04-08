package com.sbm.module.onlineleasing.interactive.bid.domain;

import lombok.Data;

import java.util.List;

@Data
public class BidResult<T> {

	private List<T> bidResultReceives;

}
