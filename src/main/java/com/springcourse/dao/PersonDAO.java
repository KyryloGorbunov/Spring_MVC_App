package com.springcourse.dao;

//@Component
public class PersonDAO {

/*    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setAddress(updatedPerson.getAddress());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }*/

    /************ JDBC Template ***********************************
     private final JdbcTemplate jdbcTemplate;

     @Autowired public PersonDAO(JdbcTemplate jdbcTemplate) {
     this.jdbcTemplate = jdbcTemplate;
     }

     public List<Person> index() {
     return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper()); //we can change PersonMapper for: new BeanPropertyRowMapper<>(Person.class)
     }

     public Optional<Person> show(String email) {
     return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[]{email}, new PersonMapper())    //we can change PersonMapper for: new BeanPropertyRowMapper<>(Person.class)
     .stream().findAny();
     }

     public Person show(int id) {
     return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper()) // new BeanPropertyRowMapper<>(Person.class)
     .stream().findAny().orElse(null);
     }

     public void save(Person person) {
     jdbcTemplate.update("INSERT INTO Person(name, age, email, address) VALUES (?, ?, ?, ?)", person.getName(), person.getAge(),
     person.getEmail(), person.getAddress());
     }

     public void update(int id, Person updatedPerson) {
     jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=?, address=? WHERE id=?", updatedPerson.getName(),
     updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getAddress(), id);
     }

     public void delete(int id) {
     jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
     }

     /*************************************************************************
     Testing the performance of a batch insert
      *************************************************************************

     public void testMultipleUpdate() {
     List<Person> people = create1000People();

     long before = System.currentTimeMillis();

     for (Person person : people) {
     jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?)", person.getId(), person.getName(), person.getAge(),
     person.getEmail());
     }

     long after = System.currentTimeMillis();

     System.out.println("Time" + (after - before));
     }

     public void testBathUpdate() {
     List<Person> people = create1000People();

     long before = System.currentTimeMillis();

     jdbcTemplate.batchUpdate("INSERT INTO Person VALUES (?, ?, ?, ?)",
     new BatchPreparedStatementSetter() {
     @Override public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
     preparedStatement.setInt(1, people.get(i).getId());
     preparedStatement.setString(2, people.get(i).getName());
     preparedStatement.setInt(3, people.get(i).getAge());
     preparedStatement.setString(4, people.get(i).getEmail());
     }

     @Override public int getBatchSize() {
     return people.size();
     }
     });


     long after = System.currentTimeMillis();

     System.out.println("Time" + (after - before));
     }

     private List<Person> create1000People() {
     List<Person> people = new ArrayList<>();

     for (int i = 0; i < 1000; i++) {
     people.add(new Person(i, "Name" + i, 30, "test" + i + "@mail.ru", "some address"));
     }
     return people;
     }
     ************************************************************************/
}
