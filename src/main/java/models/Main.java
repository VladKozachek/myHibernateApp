package models;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();
        List<Object []> products=null;
        try {
            session.beginTransaction();

            SQLQuery queryUpdate=session.createSQLQuery("UPDATE product SET title ='сметана' WHERE title='сыр'");
            queryUpdate.executeUpdate();

           SQLQuery querySelect=session.createSQLQuery("SELECT {p.*},{pc.*} FROM product p" +
                    " INNER JOIN product_category pc ON p.product_category_id=pc.id");
            querySelect.addEntity("p",Product.class);
            querySelect.addJoin("pc","p.productCategory");
           products=querySelect.list();



            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
            sessionFactory.close();
        }

        for(Object [] item:products){
            System.out.println(item[0].toString()+item[1]);
        }

    }
}
