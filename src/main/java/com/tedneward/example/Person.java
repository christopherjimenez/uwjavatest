package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  protected static int count;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    count++;
  }

  //Getters
  public int getAge() {
    return age;
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public String getSsn() {
    return ssn;
  }

  public int count() {
    return count;
  }


  //Setters
  public void setAge(int ageToSet) throws IllegalArgumentException {
    if(ageToSet < 0) {
      throw new IllegalArgumentException();
    } else {
      this.age = ageToSet;
    }
  }

  public void setName(String newName) throws IllegalArgumentException {
    if(newName == null) {
      throw new IllegalArgumentException();
    } else {
      this.name = newName;
    }
  }

  public void setSalary(double newSalary) {
    salary = newSalary;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  /*
  * AgeComparator Class
  */
  static class AgeComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }

  /*
  * getNewardFamily
  * */
  public static List getNewardFamily() {
    List<Person> family = new ArrayList<>();
    family.add(new Person("Ted", 41, 250000));
    family.add(new Person("Charlotte", 43, 150000));
    family.add(new Person("Michael", 22, 10000));
    family.add(new Person("Matthew", 15, 0));
    return family;
  }


  /*
  * compareTo method
  */
  @Override
  public int compareTo(Person other){
    double otherSalary = other.getSalary();
    if(this.salary > otherSalary) {
      return -1;
    } else if(this.salary < otherSalary) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public boolean equals(Object other) {
    boolean result = false;
    if(other instanceof Person) {
      Person other2 = (Person) other;
      if (getName().equals(other2.getName()) && getAge() == other2.getAge()) {
        result = true;
      }
    }
    return result;
  }

  @Override
  public int hashCode() {
    int stringCode = this.name.hashCode();
    int ageCode = 17 * this.age;
    return stringCode + ageCode;
  }

  @Override
  public String toString() {
    return "[Person name:" + getName() + " age:" + getAge() + " salary:" + getSalary() + "]";
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }



}
