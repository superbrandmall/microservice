package com.sbm.module.common.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface IDataRepository<T, ID extends Serializable> extends JpaRepository {
}
