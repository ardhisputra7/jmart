package com.ArdhiJmartBO;
import java.util.*;
import java.lang.Iterable;

/**
 * class untuk berisi algoritma yang bisa digunakan
 * @author Ardhi Putra
 */
public class Algorithm {

    private Algorithm() {

    }

    public static  <T> List<T> collect (T[] array, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (prd.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static  <T> List<T> collect (Iterable<T> iterable, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (prd.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static  <T> List<T> collect (Iterator<T> iterator, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (prd.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static  <T> List<T> collect (T[] array, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static  <T> List<T> collect (Iterable<T>iterable, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static  <T> List<T> collect (Iterator<T> iterator, Predicate<T> pred){
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static  <T> int count (T[] array, T value){
        int cnt = 0;
        Predicate<T> prd = val -> (val == value);
        for (T t : array) {
            if (prd.predicate(t)) {
                cnt++;
            }
        }
        return cnt;
    }
    public static  <T> int count (Iterable<T> iterable, T value){
        int cnt = 0;
        Predicate<T> prd = val -> (val == value);
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(prd.predicate(element))
            {
                cnt++;
            }
        }
        return cnt;
    }
    public static  <T> int count (Iterator<T> iterator, T value){
        int cnt = 0;
        Predicate<T> prd = val -> (val == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(prd.predicate(element))
            {
                cnt++;
            }
        }
        return cnt;
    }
    
    public static  <T> int count (T[] array, Predicate<T> pred){
        int cnt = 0;
        for (T t : array) {
            if (pred.predicate(t)) {
                cnt++;
            }
        }
        return cnt;
    }
    
    public static  <T> int count (Iterable<T> iterable, Predicate<T> pred){
        int cnt = 0;
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                cnt++;
            }
        }
        return cnt;
    }
    
    public static  <T> int count (Iterator<T> iterator, Predicate<T> pred){
        int cnt = 0;
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                cnt++;
            }
        }
        return cnt;
    }

    public static  <T> boolean exists (T[] array, T value){
        Predicate<T> prd = val -> (val == value);
        for (T t : array) {
            if (prd.predicate(t)) {
                return true;
            }
        }
        return false;
    }
    
    public static  <T> boolean exists (Iterable<T> iterable, T value){
        Predicate<T> prd = val -> (val == value);
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(prd.predicate(element))
            {
                return true;
            }
        }
        return false;
    }
    
    public static  <T> boolean exists (Iterator<T> iterator, T value){
        Predicate<T> prd = val -> (val == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(prd.predicate(element))
            {
                return true;
            }
        }
        return false;
    }
    
    public static  <T> boolean exists (T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }
    
    public static  <T> boolean exists (Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }
    
    public static  <T> boolean exists (Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    public static  <T> T find (T[] array, T value){
        Predicate<T> prd = val -> (val == value);
        for (T t : array) {
            if (prd.predicate(t)) {
                return t;
            }
        }
        return null;
    }
    
    public static  <T> T find  (Iterable<T> iterable, T value){
        Predicate<T> prd = val -> (val == value);
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(prd.predicate(element))
            {
                return element;
            }
        }
        return null;
    }
    
    public static  <T> T find (Iterator<T> iterator, T value){
        Predicate<T> prd = val -> (val == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(prd.predicate(element))
            {
                return element;
            }
        }
        return null;
    }
    
    public static  <T> T find  (T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }
    
    public static  <T> T find  (Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }
    
    public static  <T> T find  (Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    public static  <T extends Comparable<? super T>> T max(T first, T  second)
    {
        if(first.compareTo(second) > 0)
        {
            return first;
        }
        return second;
    }

    public static  <T extends Comparable<? super T>> T max(T[] array){
        T maximum = null;
        for (T t : array) {
            if (maximum == null) maximum = t;
            else if (t.compareTo(maximum) > 0) {
                maximum = t;
            }
        }
        return maximum;

    }

    public static  <T extends Comparable<? super T>> T max(Iterable<T> iterable){
        T maximum = null;
        for (T element : iterable) {
            if (maximum == null){
                maximum = element;
            }
            else if (element.compareTo(maximum) > 0){
                maximum = element;
            }
        }
        return maximum;
    }

    public static  <T extends Comparable<? super T>> T max(Iterator<T> iterator){
        T maximum = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (maximum == null){
                maximum = element;
            }
            else if (element.compareTo(maximum) > 0){
                maximum = element;
            }
        }
        return maximum;
    }

    public static  <T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator){
        int Comp = comparator.compare(first, second);
        if(Comp > 0) {
            return first;
        }
        else {
            return second;
        }
    }

    public static  <T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator){
        T maximum = array[0];
        for(int i = 0; i < array.length; i++)
        {
            int Comp = comparator.compare(array[i], maximum);
            if(Comp > 0)
            {
                maximum = array[i];
            }
        }
        return maximum;
    }

    public <T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator){
        Iterator<T> iterator = iterable.iterator();
        T maximum = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Comp = comparator.compare(element, maximum);
            if(Comp > 0)
            {
                maximum = element;
            }
        }
        return maximum;
    }

    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator){
        T maximum = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Comp = comparator.compare(element, maximum);
            if(Comp > 0)
            {
                maximum = element;
            }
        }
        return maximum;
    }

    public static  <T extends Comparable<? super T>> T min(T  first, T  second)
    {
        if(first.compareTo(second) < 0)
        {
            return first;
        }
        return second;
    }
    public static  <T extends Comparable<? super T>> T min(T[] array){
        T minimum = null;
        for (T element : array) {
            if (minimum == null) {
                minimum = element;
            }
            else if (element.compareTo(minimum) < 0) {
                minimum = element;
            }
        }
        return minimum;
    }

    public static  <T extends Comparable<? super T>> T min(Iterable<T> iterable){
        T minimum = null;
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (minimum == null){
                minimum = element;
            }
            else if (element.compareTo(minimum) < 0){
                minimum = element;
            }
        }
        return minimum;
    }

    public static  <T extends Comparable<? super T>> T min(Iterator<T> iterator){
        T minimum = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (minimum == null) {
                minimum = element;
            }
            else if (element.compareTo(minimum) < 0) {
                minimum = element;
            }
        }
        return minimum;
    }

    public static  <T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator){
        int Comp = comparator.compare(first, second);
        if (Comp < 0) {
            return first;
        } else {
            return second;
        }
    }

    public static  <T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator){
        T minimum = null;
        for (T element : array) {
            if (minimum == null){
                minimum = element;
            }
            else {
                int Comp = comparator.compare(element, minimum);
                if (Comp < 0){
                    minimum = element;
                }
            }
        }
        return minimum;
    }

    public static  <T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator){
        T minimum = null;
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (minimum == null) {
                minimum = element;
            }
            else {
                int compare = comparator.compare(element, minimum);
                if (compare < 0) {
                    minimum = element;
                }
            }
        }
        return minimum;
    }

    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator){
        T minimum = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (minimum == null) {
                minimum = element;
            }
            else {
                int compare = comparator.compare(element, minimum);
                if (compare < 0){
                    minimum = element;
                }
            }
        }
        return minimum;
    }
    
    public static<T> List<T> paginate (T[] array, int page, int pageSize, Predicate<T> pred)
	{
		List<T> pagination = new ArrayList<>();
		
		for(T lists : array) {
			if (pred.predicate(lists)) {
				pagination.add(lists);
			}
		}
	    
		if (pageSize <= 0 || page < 0) {
			throw new IllegalArgumentException("Page size tidak valid");
		}
	    int fromIndex = (page) * pageSize;
	    if(pagination == null || pagination.size() <= fromIndex){
	        return Collections.emptyList();
	    }
	    
	    // toIndex exclusive
	    return pagination.subList(fromIndex, Math.min(fromIndex + pageSize, pagination.size()));
	}
    
    public static<T> List<T> paginate (Iterable<T> iterable, int page, int pageSize, Predicate<T> pred)
	{
		List<T> pagination = new ArrayList<>();
		
		Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
        	T element = iterator.next();
			if (pred.predicate(element)) {
				pagination.add(element);
			}
		}
	    
		if (pageSize <= 0 || page < 0) {
			throw new IllegalArgumentException("Page size tidak valid");
		}
	    int fromIndex = (page) * pageSize;
	    if(pagination == null || pagination.size() <= fromIndex){
	        return Collections.emptyList();
	    }
	    
	    // toIndex exclusive
	    return pagination.subList(fromIndex, Math.min(fromIndex + pageSize, pagination.size()));
	}
    
    public static<T> List<T> paginate (Iterator<T> iterator, int page, int pageSize, Predicate<T> pred)
	{
		List<T> pagination = new ArrayList<>();
		
		while (iterator.hasNext()) {
            T element = iterator.next();
			if (pred.predicate(element)) {
				pagination.add(element);
			}
		}
	    
		if (pageSize <= 0 || page < 0) {
			throw new IllegalArgumentException("Page size tidak valid");
		}
	    int fromIndex = (page) * pageSize;
	    if(pagination == null || pagination.size() <= fromIndex){
	        return Collections.emptyList();
	    }
	    
	    // toIndex exclusive
	    return pagination.subList(fromIndex, Math.min(fromIndex + pageSize, pagination.size()));
	}

    public static<T> List<T> paginate(List<T> list, int page, int pageSize, Predicate<T> pred){
        List<T> newPage = new ArrayList<T>();

        for(T element: list) {
            if(pred.predicate(element))
                newPage.add(element);
        }

        if((pageSize < 0) || (page < 0 || page > newPage.size()/pageSize)) {
            throw new IllegalArgumentException();
        }

        int startIndex = page * pageSize;
        if(newPage == null || newPage.size() <= startIndex)
            return Collections.emptyList();

        return newPage.subList(startIndex, Math.min(startIndex + pageSize, newPage.size()));
    }
}

