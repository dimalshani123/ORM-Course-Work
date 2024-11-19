package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Program;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Session session, Student object) {
        session.update(object);
        return true;
    }

    @Override
    public boolean delete(Student value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(value);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student get(Student object) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Student ");
        List<Student> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public List<String> getIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNativeQuery("select id from Student");
        List<String> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public String getCurrentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select id from Student order by id desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public Student getObject(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Student where id = ?1");
        query.setParameter(1,value);
        Student student = (Student) query.uniqueResult();
        System.out.println(student);
        transaction.commit();
        session.close();
        return student;
    }
}
