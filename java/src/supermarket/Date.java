package supermarket;

public class Date {
	int day;
	int month;
	int year;

	public Date(int day, int month, int year)
	{
		this.day=day;
		this.month=month;
		this.year=year;
	}

	public static Date valueOf(String dateStr) {
		String[] parts = dateStr.split("-");
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);
		return new Date(day, month, year);
	}

	public boolean isValidDate() {
        if (year < 1 || month < 1 || month > 12 || day < 1) 
        {
            return false;
        }

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

       
        return day <= daysInMonth[month - 1];
    }
	
	@Override
	public String toString() 
	{
		return day + "-" + month + "-" + year;
	}

	// checking if The date is valid.
	public boolean isLaterThan(Date other) {
		if (this.year != other.year) {
			return this.year > other.year;
		}
		if (this.month != other.month) {
			return this.month > other.month;
		}
		return this.day > other.day;
	}

	public boolean compareTo(Date other) {
		if(this.day!= other.day || this.month!=other.month || this.year!=other.year)
			return false;
		return true;


	}

}

