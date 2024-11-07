//package lk.ijse.dao.custom.impl;
//
//import lk.ijse.config.FactoryConfiguration;
//import lk.ijse.dao.custom.PaymentDAO;
//import lk.ijse.entity.Payment;
//import lk.ijse.entity.Program;
//import lk.ijse.entity.Student;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.NativeQuery;
//import org.hibernate.query.Query;
//
//import java.util.List;
//
//public class PaymentDAOImpl implements PaymentDAO {
//    @Override
//    public boolean save(Payment object) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(object);
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//    @Override
//    public boolean update(Payment object) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(object);
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//    @Override
//    public boolean delete(Payment object) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.delete(object);
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//    @Override
//    public Payment get(Payment object) {
//        return null;
//    }
//
//    @Override
//    public List<Payment> getAll() {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
////        List<Customer> resultList1 = new ArrayList<>();
////        session.find(Customer.class,Hha);
//        NativeQuery query = session.createNativeQuery("SELECT * FROM Payment");
//        query.addEntity(Payment.class);
//        List<Payment> resultList = query.getResultList();
//        transaction.commit();
//        session.close();
//        return resultList;
//    }
//
//    @Override
//    public List<String> getIds() {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery(" select id from Payment");
//        List<String> list = query.getResultList();
//        transaction.commit();
//        session.close();
//        return list;
//    }
//
//    @Override
//    public String getCurrentId() {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("select id from Payment order by id desc limit 1");
//        String id = (String) query.uniqueResult();
//        transaction.commit();
//        session.close();
//        return id;
//    }
//
//    @Override
//    public Payment getObject(String value) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("from Payment where id = ?1");
//        query.setParameter(1,value);
//        Payment payment = (Payment) query.uniqueResult();
//        transaction.commit();
//        session.close();
//        return payment;
//    }
//
//    @Override
//    public boolean update(Session session, Payment object) {
//        return false;
//    }
//}
