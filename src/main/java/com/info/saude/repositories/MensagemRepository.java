package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.saude.domain.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

}