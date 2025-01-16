import { render, screen } from "../test-utils";
import  MatchesListing from "./index";

describe('MatchesListing', () => {
        test('renders matches names correctly (as table cells)', async () => {
        render(<MatchesListing/>);

        for (const value of ['Kasparov vs DeepBlue', 'The immortal', 'Game of the Century','The Opera Game']) {
            const el = await screen.findByRole('cell', { 'name': value });
            expect(el).toBeInTheDocument();
        }
    });

    test('Renders participants names correctly (as items in a list)', async () => {
        render(<MatchesListing/>);
        const listitems = await screen.findAllByRole('listitem');
        const p1 = listitems.find(listitem => listitem.textContent === 'Gary Kasparov')
        expect(p1).toBeInTheDocument();

        const p2 = listitems.find(listitem => listitem.textContent === 'Donald Byrne')
        expect(p2).toBeInTheDocument();

        const p3 = listitems.find(listitem => listitem.textContent === 'Bobby Fischer')
        expect(p3).toBeInTheDocument();
    });

});