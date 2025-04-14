@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository repository;

    @Test
    void findByAge_shouldReturnStudents() {
        Student student = new Student("Harry", 17);
        repository.save(student);

        List<Student> result = repository.findByAge(17);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Harry");
    }
}