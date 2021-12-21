import React from 'react';

const Teams = ({teams}) => {
  return (
      <div>
        <center><h1>Contact List</h1></center>
        {teams.map((t) => (
            <div class="card">
              <div class="card-body">
                <h6 class="card-title mb-2">{t.id} {t.season}</h6>
                <h6 class="card-title mb-2">{t.homeTeam} vs {t.awayTeam} : {t.HScore}({t.HxG}) - {t.AScore}({t.AxG})</h6>
              </div>
            </div>
        ))}
      </div>
  );
};

export default Teams;
