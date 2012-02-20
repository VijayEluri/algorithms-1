package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Term implements Comparable<Term> {
  final int coefficient;
  final int degree;

  public Term(int coefficient, int degree) {
    this.coefficient = coefficient;
    this.degree = degree;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + coefficient;
    result = prime * result + degree;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final Term other = (Term) obj;
    if (coefficient != other.coefficient)
      return false;
    if (degree != other.degree)
      return false;
    return true;
  }

  public Term add(Term t) {
    if (t.degree != degree)
      throw new IllegalArgumentException("degress don't match");
    return new Term(coefficient + t.coefficient, degree);
  }

  public static Term addAll(List<Term> terms) {
    if (terms.isEmpty())
      return new Term(0, 0);

    Term term = terms.get(0);
    for (final Term t : terms.subList(1, terms.size()))
      term = term.add(t);
    return term;
  }

  public Term mult(Term t) {
    return new Term(coefficient * t.coefficient, degree + t.degree);
  }

  static Term buildTerm(String s) {
    return new Term(coefficient(s), degree(s));
  }

  private static int degree(String s) {
    if (s.indexOf('x') == -1)
      return 0;
    if (s.indexOf('^') == -1)
      return 1;

    return Integer.parseInt(s.substring(s.indexOf('^') + 1));
  }

  private static int coefficient(String s) {
    int lastIndex;
    if (s.indexOf('x') == -1)
      lastIndex = s.length();
    else
      lastIndex = s.indexOf('x');

    if (s.charAt(0) == '+')
      return Integer.parseInt(s.substring(1, lastIndex));
    return Integer.parseInt(s.substring(0, lastIndex));
  }

  @Override
  public String toString() {
    if (degree == 0)
      return String.valueOf(coefficient);
    return "(" + coefficient + "x^" + degree + ")";
  }

  
  @Override
  public int compareTo(Term o) {
    return o.degree - degree;
  }
}

public class Polynomial {
  List<Term> terms;

  public Polynomial(List<Term> terms) {
    this.terms = terms;
  }

  public Polynomial add(Polynomial p) {
    return new Polynomial(addTerms(terms, p.terms));
  }

  public Polynomial mult(Polynomial p) {
    return new Polynomial(multTerms(terms, p.terms));
  }

  private static List<Term> multTerms(List<Term> terms1, List<Term> terms2) {
    final List<Term> terms = new ArrayList<Term>();
    for (final Term term : terms1) {
      terms.addAll(multTerms(term, terms2));
    }
    return merge(terms);
  }

  private static List<Term> merge(List<Term> terms) {
    final Map<Integer, List<Term>> map = new HashMap<Integer, List<Term>>();
    for (final Term t : terms) {
      List<Term> ts = map.get(t.degree);
      if (ts == null)
        map.put(t.degree, ts = new ArrayList<Term>());
      ts.add(t);
    }

    final List<Term> merged = new ArrayList<Term>();
    for (final List<Term> ts : map.values())
      merged.add(Term.addAll(ts));
    return merged;
  }

  private static List<Term> multTerms(Term term1, List<Term> terms2) {
    final List<Term> terms = new ArrayList<Term>();
    for (final Term term : terms2)
      terms.add(term1.mult(term));
    return terms;
  }

  private static List<Term> addTerms(List<Term> terms1, List<Term> terms2) {
    if (terms1.isEmpty())
      return terms2;

    if (terms2.isEmpty())
      return terms1;

    final Term term1 = terms1.get(0);
    final Term term2 = terms1.get(0);

    final List<Term> terms = new ArrayList<Term>();
    if (term1.degree == term2.degree) {
      terms.add(term1.add(term2));
      terms.addAll(addTerms(terms1.subList(1, terms1.size()), terms2.subList(1, terms2.size())));
    } else if (term1.degree > term2.degree) {
      terms.add(term1);
      terms.addAll(addTerms(terms1.subList(1, terms1.size()), terms2));
    } else {
      terms.add(term2);
      terms.addAll(addTerms(terms1, terms2.subList(1, terms2.size())));
    }
    return terms;
  }

  static Polynomial buildPolynomial(String s) {
    final List<Term> terms = new ArrayList<Term>();
    String op = "+";
    for (final String t : s.split(" +")) {
      if (!t.equals("+") && !t.equals("-"))
        terms.add(Term.buildTerm(op + t));
      else
        op = t;
    }
    
    Collections.sort(terms);
    return new Polynomial(terms);
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    for (final Term t : terms) {
      sb.append(t).append(" + ");
    }
    return sb.substring(0, sb.length() - 3);
  }

  public static void main(String[] args) {
    System.out.println(Polynomial.buildPolynomial("3"));
    System.out.println(Polynomial.buildPolynomial("3x^2 + 1x + 2").mult(Polynomial.buildPolynomial("3x^2 + 4x^2")));
  }
}
