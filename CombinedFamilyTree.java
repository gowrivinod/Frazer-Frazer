package com.example.familytree;

import java.util.*;

class Person {
    String name;
    Person father;
    Person mother;
    Person spouse;

    Person(String name) {
        this.name = name;
    }
}

public class CombinedFamilyTree {

    public static boolean isInLaw(Person person, Person referencePerson) {
        return person.spouse != null && (person.spouse.father == referencePerson || person.spouse.mother == referencePerson);
    }
    public static boolean isSpouse(Person person1, Person person2) {
        return person1.spouse == person2 || person2.spouse == person1;
    }

    public static boolean isParent(Person potentialParent, Person potentialChild) {
        return potentialChild.father == potentialParent || potentialChild.mother == potentialParent;
    }

    public static boolean isSibling(Person person1, Person person2) {
        return person1.father == person2.father && person1.mother == person2.mother;
    }

    public static boolean isGrandparent(Person potentialGrandparent, Person potentialGrandchild) {
        return isParent(potentialGrandparent, potentialGrandchild.father) ||
                isParent(potentialGrandparent, potentialGrandchild.mother);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating family members and relationships for FamilyTree
        Person paternalGrandfather = new Person("Paternal Grandfather");
        Person paternalGrandmother = new Person("Paternal Grandmother");
        Person maternalGrandfather = new Person("Maternal Grandfather");
        Person maternalGrandmother = new Person("Maternal Grandmother"); // Add missing semicolon here
        Person father = new Person("Father");
        Person mother = new Person("Mother");
        Person son = new Person("Son");
        Person daughter = new Person("Daughter");
        Person sonWife = new Person("Son's Wife");		
        Person sonChild = new Person("Son's Child");
        Person daughterHusband = new Person("Daughter's Husband");
        Person daughterChild = new Person("Daughter's Child");

        // Establishing relationships for FamilyTree
        paternalGrandfather.spouse = paternalGrandmother;
        paternalGrandmother.spouse = paternalGrandfather;
        maternalGrandfather.spouse = maternalGrandmother;
        maternalGrandmother.spouse = maternalGrandfather;
        father.father = paternalGrandfather;
        father.mother = paternalGrandmother;
        mother.father = maternalGrandfather;
        mother.mother = maternalGrandmother;
        son.father = father;
        son.mother = mother;
        daughter.father = father;
        daughter.mother = mother;
        
        son.spouse = sonWife;
        sonWife.spouse = son;
        sonChild.father = son;
        sonChild.mother = sonWife;
        daughter.spouse = daughterHusband;
        daughterHusband.spouse = daughter;
        daughterChild.mother = daughter;
        daughterChild.father = daughterHusband;

        father.spouse = mother; // Establishing the spouse relationship
        mother.spouse = father; // Establishing the spouse relationship
        
     // Establishing the in-law relationship between son's wife and his parents
        sonWife.father = father;
        sonWife.mother = mother;
     // Establishing the in-law relationship between daughter's husband and her parents
        daughterHusband.father = father;
        daughterHusband.mother = mother;

        paternalGrandfather.father = null;
        paternalGrandfather.mother = null;
        paternalGrandmother.father = null;
        paternalGrandmother.mother = null;
        maternalGrandfather.father = null;
        maternalGrandfather.mother = null;
        maternalGrandmother.father = null;
        maternalGrandmother.mother = null;

        // Creating a map to store relationships for FamilyRelationshipPredictor
        Map<String, Person> familyMap = new HashMap<>();
        familyMap.put("paternalGrandfather", paternalGrandfather);
        familyMap.put("paternalGrandmother", paternalGrandmother);
        familyMap.put("maternalGrandfather", maternalGrandfather);
        familyMap.put("maternalGrandmother", maternalGrandmother);
        familyMap.put("father", father);
        familyMap.put("mother", mother);
        familyMap.put("son", son);
        familyMap.put("daughter", daughter);
        familyMap.put("sonWife", sonWife);
        familyMap.put("sonChild", sonChild);
        familyMap.put("daughterHusband", daughterHusband);
        familyMap.put("daughterChild", daughterChild);

        // Combined code for taking input and determining relationships
        System.out.print("Enter the first person's name: ");
        String person1Name = scanner.nextLine();

        System.out.print("Enter the second person's name: ");
        String person2Name = scanner.nextLine();

        Person person1 = familyMap.get(person1Name);
        Person person2 = familyMap.get(person2Name);

        if (person1 != null && person2 != null) {
            if (isInLaw(person1, person2)) {
                System.out.println(person1.name + " is a in-laws of " + person2.name);
            } else if (isInLaw(person2, person1)) {
                System.out.println(person2.name + " is a in-laws of " + person1.name);
            } else if (isSpouse(person1, person2)) {
                System.out.println(person1.name + " and " + person2.name + " are spouses");
            } else if (isParent(person1, person2)) {
                System.out.println(person1.name + " is the parent of " + person2.name);
            } else if (isParent(person2, person1)) {
                System.out.println(person2.name + " is the parent of " + person1.name);
            } else if (isSibling(person1, person2)) {
                System.out.println(person1.name + " and " + person2.name + " are siblings");
            } else if (isGrandparent(person1, person2)) {
                System.out.println(person1.name + " is the grandparent of " + person2.name);
            } else if (isGrandparent(person2, person1)) {
                System.out.println(person2.name + " is the grandparent of " + person1.name);
            } else if (isGrandparent(person1, person2.father) || isGrandparent(person1, person2.mother)) {
                System.out.println(person1.name + " is the grandparent of " + person2.name);
            } else if (isGrandparent(person2, person1.father) || isGrandparent(person2, person1.mother)) {
                System.out.println(person2.name + " is the grandparent of " + person1.name);
            } else {
                System.out.println("Relationship between " + person1.name + " and " + person2.name + " is not determined.");
            }
        } else {
            System.out.println("Person not found in the family tree.");
        }
    }
}