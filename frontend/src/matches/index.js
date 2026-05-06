import React from "react";

export default function MatchesListing() {
  const matchesData = [
    {
      id: 1,
      name: "Kasparov vs DeepBlue",
      participants: ["Gary Kasparov", "DeepBlue"],
    },
    {
      id: 2,
      name: "The immortal",
      participants: ["Adolf Anderssen", "Lionel Kieseritzky"],
    },
    {
      id: 3,
      name: "Game of the Century",
      participants: ["Donald Byrne", "Bobby Fischer"],
    },
    {
      id: 4,
      name: "The Opera Game",
      participants: ["Paul Morphy", "Duke Karl / Count Isouard"],
    },
  ];

  return (
    <div>
      <h1>Matches Listing</h1>
      
      <table border="1" style={{ marginTop: "20px", borderCollapse: "collapse" }}>
        <thead>
          <tr>
            <th>Match Name</th>
            <th>Participants</th>
          </tr>
        </thead>
        <tbody>
          {matchesData.map((match) => (
            <tr key={match.id}>
              <td>{match.name}</td>
              
              <td>
                <ul style={{ margin: 0, paddingLeft: "20px" }}>
                  {match.participants.map((participant, index) => (
                    <li key={index}>{participant}</li>
                  ))}
                </ul>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
