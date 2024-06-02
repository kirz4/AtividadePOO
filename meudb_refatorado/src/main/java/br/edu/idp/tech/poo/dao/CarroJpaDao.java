package br.edu.idp.tech.poo.dao;

import br.edu.idp.tech.poo.entidade.Carro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CarroJpaDao implements CarroDao {

    private EntityManager entityManager;

    public CarroJpaDao() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bd_carros");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void salvar(Carro carro) {
        entityManager.getTransaction().begin();
        entityManager.persist(carro);
        entityManager.getTransaction().commit();
    }

    @Override
    public void salvar(List<Carro> novosCarros) {
        for (Carro carro : novosCarros) {
            this.salvar(carro);
        }
    }

    @Override
    public List<Carro> findAll() {
        List<Carro> carrosNoJpa = entityManager
                .createQuery("from Carro", Carro.class)
                .getResultList();
        return carrosNoJpa;
    }

    @Override
    public boolean tableExists() {
        // No JPA, não faz sentido verificar a existência da tabela dessa forma
        // Retornamos true para evitar problemas de compilação, mas não há lógica real aqui
        return true;
    }

    @Override
    public void createTable() {
        throw new UnsupportedOperationException("Não é possível criar tabela explicitamente com JPA. Use anotações nas entidades para definir a estrutura do banco.");
    }
}
