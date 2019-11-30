package stud.oiv.migration.beans;

import java.util.ArrayList;
import java.util.Random;

public  class Identifier
{
    private int Id;

    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public static boolean isUniq(int id, ArrayList<Identifier> identifiers)
    {
        for(Identifier identifier: identifiers)
        {
            if(identifier.getId() == id)
                return false;
        }
        return true;
    }

    public static int getUniq(ArrayList<Identifier> identifiers)
    {
        Random random = new Random(System.currentTimeMillis());
        boolean isUniq;
        int uniqId;
        do {
            isUniq = true;
            uniqId = Math.abs(random.nextInt());
            for (Identifier identifier : identifiers) {
                if (identifier.getId() == uniqId)
                    isUniq = false;
            }
        }while (!isUniq);
        return uniqId;
    }
}
