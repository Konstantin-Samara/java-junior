
/**
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 *
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
 */
package org.example;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static String[] names = {"Иван","Игорь","Михаил","Дмитрий","Валерий","Олег","Николай","Сергей"
            ,"Владимир","Павел","Алексей","Александр","Виктор","Петр","Федор","Рустам","Руслан"
            ,"Ольга","Наталья","Зинаида"};
    static String[] departments = {"Производство","Финансы","Кадры","Безопасность"};

    public static void main(String[] args) {
        /*TASK1*/
        ArrayList<Integer> list;
        createRandomList(list = new ArrayList<>());
        System.out.print(
                "\nMAX = "+list.stream().max(Comparator.comparingInt(o -> o)).get()+
                "\nSUM = "+list.stream().filter(n -> n>500000).mapToLong(it -> it*5-150).sum()+
                "\nCount = "+list.stream().filter(z->z<Math.sqrt(100000)).count()+"\n");
        /*TASK2*/

        /*2.1*/
        System.out.println();
        ArrayList<Employee> employees;
        createRandomEmployees(employees = new ArrayList<>());
        for (Employee item:employees) {
            System.out.println(item.toString());
        }
        /*2.2*/
        System.out.println();
        employees.stream()
                .map(n->n.getDepartment())
                .distinct()
                .forEach(System.out::println);
        /*2.3*/
        System.out.println();
        employees.stream()
                .filter(n->n.salary<10000)
                .peek(n->n.setSalary(n.getSalary()*1.2))
                .forEach(System.out::println);
        HashMap<String,List<Employee>> map1 = new HashMap<>();
        HashMap<String,Double> map2 = new HashMap<>();
        for (String item:departments) {
            List<Employee> list1;
            list1 = employees.stream()
                    .filter(n->n.getDepartment().equals(item))
                    .collect(Collectors.toList());
            map1.put(item,list1);
            Double middleSalary = (employees.stream()
                    .filter(n->n.getDepartment().equals(item))
                    .mapToDouble(n->n.getSalary()).sum())/list1.size();
            map2.put(item,middleSalary);
        }
        System.out.println();
        System.out.println(map1);
        System.out.println();
        System.out.println(map2);
    }
    static void createRandomList(ArrayList<Integer> list){

        for (int i = 0; i < 1000; i++) {
            list.add(new Random().nextInt(1, 1000000));
        }
    }
    static void createRandomEmployees(ArrayList<Employee> employees){

        for (int i = 0; i < names.length; i++) {
             Employee employee = new Employee(
                    names[i],
                    new Random().nextInt(20,60),
                    new Random().nextDouble(5000,50000),
                    departments[new Random().nextInt(0,departments.length)]);
            employees.add(employee);
        }
    }
    static class Employee {
        private String name;
        private int age;
        private double salary;
        private String department;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }
        Employee(String name, int age, double salary, String department){
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.department = department;
        }
        @Override
        public String toString(){
            return "NAME = "+this.name+" AGE = "+this.age+" SALARY = "
                    +this.salary+" DEPARTMENT = "+this.department;
        }
    }

}