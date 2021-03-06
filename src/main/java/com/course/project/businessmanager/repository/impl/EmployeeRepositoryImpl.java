package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Employee;
import com.course.project.businessmanager.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class EmployeeRepositoryImpl extends BasicRepositoryImpl<Employee, UUID> implements EmployeeRepository {

    /**
     * The method used for getting employees info by their owner email from database
     *
     * @param email String email used to find employees by it
     * @return UUID
     */
    @Override
    public Optional<List<Employee>> findEmployeesByBossEmail(String email) {
        log.info("Enter into findEmployeesByBossEmail  with email:{}", email);
        TypedQuery<Employee> query = getSession().createNamedQuery("findEmployeesByBoss", Employee.class);
        query.setParameter("email", email);
        List<Employee> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList());
    }

    /**
     * The method used for getting boss info by their owner email from database
     *
     * @param id String email used to find employees by it
     * @return UUID
     */
    @Override
    public Optional<Employee> findBoss(UUID id) {
        log.info("Enter into findBoss  with building id:{}", id);
        TypedQuery<Employee> query = getSession().createNamedQuery("findBoss", Employee.class);
        query.setParameter("id", id);
        List<Employee> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList().get(0));
    }

    @Override
    public Optional<Employee> findByEmail(String email, String buildingName) {
        log.info("Enter into findBoss  with building name:{}", buildingName);
        TypedQuery<Employee> query = getSession().createNamedQuery("findByEmail", Employee.class);
        query.setParameter("buildingName", buildingName);
        query.setParameter("email", email);
        List<Employee> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList().get(0));
    }

    /**
     * The method used for getting number of employees under boss by email from database
     *
     * @param email String email used to find count of employees
     * @return Long number of records with name
     */
    @Override
    public Long countEmployeesByBossEmail(String email) {
        log.info("In countEmployeesByBossEmail(email = [{}])", email);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Employee e " +
                        "join e.position p where p.employee.email = :email")
                .setParameter("email", email).getSingleResult();
    }

    /**
     * The method used for getting number of employee with email from database
     *
     * @param email String email used to find count of employees
     * @return Long number of records with name
     */
    @Override
    public Long countEmployeeWithEmail(String email) {
        log.info("In countEmployeeWithEmail(email = [{}])", email);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Employee e " +
                        "where e.email = :email")
                .setParameter("email", email).getSingleResult();
    }
}
