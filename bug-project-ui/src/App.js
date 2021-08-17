import logo from "./logo.svg";
import "./App.css";

import HomePage from "./components/homePage";
import { Footer } from "./components/footer";
function App() {
  return (
    <div className="App">
      <header className="App-header"></header>
      <HomePage></HomePage>
      <Footer />
    </div>
  );
}

export default App;
