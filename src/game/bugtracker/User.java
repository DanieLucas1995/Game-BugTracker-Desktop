
package game.bugtracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private int userID;
    private String name;
    private String userName;
    private int age;
    private String email;
    private String password;  
    private String accessType;
    private String gender;
    private String phoneNumber;
    private Date dateJoined;  // representa a data de inscrição
    private String bio;
    private Company company;  
    private List<Task> tasks; 
    private List<Notification> notifications; 
    private List<Comment> comments;
    private List<Report> reports;
    private Integer companyId; // Chave estrangeira referenciando a empresa associada (pode ser nulo)
    private Integer taskId;  // Chave estrangeira referenciando a tarefa atribuída (pode ser nulo)
    private Integer notificationId;  // Chave estrangeira referenciando a notificação atribuída (pode ser nulo)
    private Integer commentId;  // Chave estrangeira referenciando o comentário atribuído ao usuário(pode ser nulo)
    private Integer reportId;  // Chave estrangeira referenciando o relatório atribuído ao usuário(pode ser nulo)

   
    public String accessType(String enteredPassword) {
    // Logic to determine user type based on username and password

 // Verifica se a senha inserida é igual à senha armazenada no objeto User
    if (enteredPassword.equals(this.password)) {
        // Retorna o tipo de acesso com base no nome de usuário
        if ("Administrator".equals(userName)) {
            return "Administrator";
        } else if ("Developer".equals(userName)) {
            return "Developer";
        } else if ("Tester/QA (Quality Assurance)".equals(userName)) {
            return "Tester/QA (Quality Assurance)";
        } else if ("Viewer".equals(userName)) {
            return "Viewer";
        } else if ("Guest/Client".equals(userName)) {
            return "Guest/Client";
        } else if ("Bug Reporter".equals(userName)) {
            return "Bug Reporter";
        } else {
            return "Unknown";
        }
    } else {
        return "Unknown"; // Senha incorreta
    }
}

 

  
// Construtor

    public User(int userID, String name, String userName, int age, String email, String password,
            String accessType, String gender, String phoneNumber, Date dateJoined, String bio,
            Integer companyId, Integer taskId, Integer notificationId, Integer commentId, Integer reportId) {
        
        this.userID = userID;
        this.name = name;
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.accessType = accessType;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateJoined = dateJoined;
        this.bio = bio;
        this.companyId = companyId;
        this.taskId = taskId;
        this.notificationId = notificationId;
        this.commentId = commentId;  
        // (outras inicializações, como tasks e notifications)
        // Inicializa as listas vazias para tarefas e notificações
        tasks = new ArrayList<>();
        notifications = new ArrayList<>();
        comments = new ArrayList<>();
        reports = new ArrayList<>();

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    

}
