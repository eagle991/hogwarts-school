@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    @Test
    void createStudent_shouldSaveToRepository() {
        Student student = new Student("Hermione", 18);
        when(repository.save(any())).thenReturn(student);

        Student result = service.createStudent(student);

        verify(repository).save(student);
        assertThat(result.getName()).isEqualTo("Hermione");
    }
}