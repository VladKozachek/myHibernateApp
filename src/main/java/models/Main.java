package models;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

import java.math.BigDecimal;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();
        List<Product> products=null;
        try {
            session.beginTransaction();


            // SQL запросы
            /*SQLQuery queryUpdate=session.createSQLQuery("UPDATE product SET title = :title WHERE id=:id");
            queryUpdate.setParameter("title","кефир");
            queryUpdate.setParameter("id",7);
            queryUpdate.executeUpdate();

           SQLQuery querySelect=session.createSQLQuery("SELECT {p.*},{pc.*} FROM product p" +
                    " INNER JOIN product_category pc ON p.product_category_id=pc.id");
            querySelect.addEntity("p",Product.class);
            querySelect.addJoin("pc","p.productCategory");
           products=querySelect.list(); */

            long arr[] ={7,8,5};
            long a=2,b=20;
            BigDecimal decimial = BigDecimal.valueOf(1);


            //Criteria запросы
            Criteria criteria = session.createCriteria(Product.class,"product");
            //  criteria.add(Restrictions.eq("title","молоко" )); //сравниваем поле "title" в таблице с записью "молоко"
                                                                // аналог SQL : SELECT * FROM product WHERE title="кефир";
            // criteria.add(Restrictions.eq("id",arr[2])); // сравниваем поле "id" в таблице с записью "8"
            //criteria.add(Restrictions.between("id",a,b )); // поля которые в диапазоне от 10 до 20
           // criteria.add(Restrictions.like("title", "кеф%"));// поля котрые начинаются с "кеф"

            criteria.createCriteria("product.productCategory","productCategory"); //обьеденяем таблицы
            criteria.add(Restrictions.eq("productCategory.id",a)); //условие выодит все категори котрые имеют id=2
            products=criteria.list();


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

        for(Product  product: products){
            System.out.println(product.toString());
        }

    }
}
