

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class PersonTest
{
	                Session     session = null;
	                Session     session2 = null;
	private  final  String[][]  persons = {{"21", "J-EUG"}, {"22", "T-FUCK"}};
	//private static final Logger logr = LogManager.getLogger(PersonTest.class.getName());
	private static final Logger log = LogManager.getLogger(PersonTest.class);
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Процедура создания сессии
	 * @return org.hibernate.Session
	 */
	private Session createHibernateSession()
	{
		SessionFactory   sessionFactory  = null;
		ServiceRegistry  serviceRegistry = null;
		
		SessionFactory   sessionFactory2  = null;
		ServiceRegistry  serviceRegistry2 = null;
		
		
		try {
			try {
				Configuration cfg = new Configuration().addResource("person.hbm.xml").configure();
				Configuration cfg2 = new Configuration().addResource("Createtst.hbm.xml").configure();
				
				
				
				serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
				serviceRegistry2 = new StandardServiceRegistryBuilder().applySettings(cfg2.getProperties()).build();
				
				
				sessionFactory = cfg.buildSessionFactory(serviceRegistry);
				sessionFactory2 = cfg2.buildSessionFactory(serviceRegistry);
				
				new SchemaExport(cfg2).create(false, true);
				
			} catch (Throwable e) {
				System.err.println("Failed to create sessionFactory object." + e);
				throw new ExceptionInInitializerError(e);
			}
			session = sessionFactory.openSession();
			System.out.println("Создание сессии 1");
			
			session2 = sessionFactory2.openSession();
			System.out.println("Создание сессии 2");
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return session;
		
		
		
		
		
		
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Процедура добавления записей в таблицу
	 */
	private void recordsAdd()
	{
		try {
			System.out.println("Добавление записи в таблицу БД");
			Transaction tx = session.beginTransaction();
			for (int i = 0; i < persons.length; i++) {
				Person person = new Person();
				person.setId(Integer.valueOf(persons[i][0]));
				person.setName(persons[i][1]);
				session.save(person);
			}
			tx.commit();
			System.out.println("Записи добавлены");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Процедура чтения записей
	 */
	private void recordsRead()
	{
        System.out.println("\nЧтение записей таблицы");
		//String query = "select * from " + Person.class.getSimpleName() + " p";
        String query = "FROM " + Person.class.getSimpleName() + "";
			
        @SuppressWarnings("unchecked")
		List<Person> list = (List<Person>)session.createQuery(query).list();
        System.out.println(list);
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Процедура поиска записи
	 */
	private void recordFind(final int id)
	{
        System.out.println("\nЧтение записи таблицы по ID");
		Person person = (Person) session.load(Person.class, id);
        System.out.println(person);
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Конструктор класса
	 */
	public PersonTest()
	{
		// Создание сессии
		session = createHibernateSession();
		if (session != null) {
			// Добавление записей в таблицу
            recordsAdd();
            // Чтение записей таблицы
            recordsRead();
            // Поиск записи по идентификатору 
           // recordFind(Integer.valueOf(persons[1][0]));
            // Закрытие сессии
            
            if (session.isOpen())
                session.close();
            
            
        }
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public static void main(String[] args)
	{
		
	

		System.out.println("Заказ оформлен!");
	
		//logr.debug("Debug Message Logged !!!");
		log.debug("Debug Message Logged !!!");
        //logr.info("Info Message Logged !!!");
        log.info("Info Message Logged -палыимьпалыь!!!");
		new PersonTest();
		System.exit(0);
	}
}