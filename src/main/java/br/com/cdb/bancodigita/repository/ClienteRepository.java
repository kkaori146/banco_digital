package br.com.cdb.bancodigita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigita.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
