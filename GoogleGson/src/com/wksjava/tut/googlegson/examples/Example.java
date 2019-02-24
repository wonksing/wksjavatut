package com.wksjava.tut.googlegson.examples;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Example {
	public static class Person {
		private String name;
		private int age;
		List<Person> child = new LinkedList<Person>();
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
		public List<Person> getChild() {
			return child;
		}
		public void setChild(List<Person> child) {
			this.child = child;
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + ", child=" + child + "]";
		}
		
	}
	
	public static void main(String[] args) {
		
		// prepare object, Person
		Person person = new Person();
		person.setName("wonk");
		person.setAge(38);
		
		// prepare child
		Person daughter = new Person();
		daughter.setName("sia");
		daughter.setAge(5);
		
		List<Person> child = new LinkedList<Person>();
		child.add(daughter);
		person.setChild(child);
		
		
		System.out.println("object to json");
		String personJson = new Gson().toJson(person);
		System.out.println(personJson);

		System.out.println("json to object");
		Person reverted = new Gson().fromJson(personJson, Person.class);
		System.out.println(reverted);
		

		System.out.println("object to json");
		person.setName(null);
		personJson = new GsonBuilder().serializeNulls().create().toJson(person);
		System.out.println(personJson);

		System.out.println("json to object");
		Person reverted2 = new Gson().fromJson(personJson, Person.class);
		System.out.println(reverted2);
	}
	

	
}
