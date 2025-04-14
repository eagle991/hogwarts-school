@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Test
    void getStudent_shouldReturn200() throws Exception {
        when(service.findStudent(1L)).thenReturn(Optional.of(new Student("Ron", 16)));

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ron"));
    }
}